import { createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit';
import axios from 'axios';
import { RootState } from '..';
import { ISearch, TProduct } from './types';
import { BASE_URL } from './../../constants';

interface ISearchState {
    result: TProduct[];
    isLoading: boolean;
    error: string;
}

const initialState: ISearchState = {
    result: [],
    isLoading: false,
    error: '',
};

export const searchSelectors = {
    getProducts: (state: RootState) => state.searchReducer.result,
    getIsLoading: (state: RootState) => state.searchReducer.isLoading
};

export const searcActions = {
    search: createAsyncThunk('search/Search', async (data: ISearch) => {
        const { name, page, size } = data;
        const response = await axios.get<TProduct[]>(`${BASE_URL}/api/v1/product?name=${name}&page=${page}&size=${size}`);
        return response.data;
    }),
};

const searchSlice = createSlice({
    name: 'search',
    initialState,
    reducers: {
    },
    extraReducers: {
		[searcActions.search.pending.type]: (state) => {
            state.isLoading = true;
        },
        [searcActions.search.fulfilled.type]: (
            state,
            action: PayloadAction<TProduct[]>,
        ) => {
			state.result = action.payload;
            state.isLoading = false;
            state.error = '';
        },
        [searcActions.search.rejected.type]: (state, action: PayloadAction<string>) => {
            state.isLoading = false;
            state.error = action.payload;
        },
    },
});

export default searchSlice.reducer;
