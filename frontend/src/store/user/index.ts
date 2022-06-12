import { createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit';
import axios from 'axios';
import { RootState } from '..';
import { IUser } from './types';
import { BASE_URL } from './../../constants';

interface UserState {
    user: IUser;
    isLoading: boolean;
    error: string;
}

type TUserSignUp = Omit<IUser, 'id' | 'role' | 'company'> & { password: string };
type TUserLogin = Pick<IUser, 'email'> & { password: string };

const initialState: UserState = {
    user: {
        id: 0,
        firstName: '',
        lastName: '',
        patronymic: '',
        email: '',
        role: {
			name: 'user'
		},
    },
    isLoading: false,
    error: '',
};

export const userSelectors = {
    getUser: (state: RootState) => state.userReducer.user,
    getIsAuth: (state: RootState) => !!state.userReducer.user.id,
};

export const userActions = {
    loginUser: createAsyncThunk('users/loginUser', async (data: TUserLogin) => {
        const { email, password } = data;
        const response = await axios.post<TUserSignUp>(`${BASE_URL}/api/v1/login`, {
            email,
            password,
        });

		if (response.status === 200) {
            localStorage.setItem('token', '1');
        }

        return response.data;
    }),
    signUpUser: createAsyncThunk('users/signupUser', async (data: TUserSignUp) => {
        const { firstName, lastName, patronymic, email, password } = data;
        const response = await axios.post<TUserSignUp>(`${BASE_URL}/api/v1/user`, {
            firstName,
            lastName,
            patronymic,
            email,
            password,
        });

        if (response.status === 200) {
            localStorage.setItem('token', '1');
        }

        return response.data;
    }),
};

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {},
    extraReducers: {
		[userActions.loginUser.pending.type]: (state) => {
            state.isLoading = true;
        },
        [userActions.loginUser.fulfilled.type]: (
            state,
            action: PayloadAction<IUser>,
        ) => {
			state.user = action.payload;
            state.isLoading = false;
            state.error = '';
        },
        [userActions.loginUser.rejected.type]: (state, action: PayloadAction<string>) => {
            state.isLoading = false;
            state.error = action.payload;
        },
        [userActions.signUpUser.pending.type]: (state) => {
            state.isLoading = true;
        },
        [userActions.signUpUser.fulfilled.type]: (
            state,
            action: PayloadAction<IUser>,
        ) => {
            state.user = action.payload;
            state.isLoading = false;
            state.error = '';
        },
        [userActions.signUpUser.rejected.type]: (state, action: PayloadAction<string>) => {
            state.isLoading = false;
            state.error = action.payload;
        },
    },
});

export default userSlice.reducer;
