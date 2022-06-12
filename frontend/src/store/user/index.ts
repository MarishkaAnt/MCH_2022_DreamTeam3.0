import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";   
import { RootState } from "..";
import { IUser } from "./types";

interface UserState {
	user: IUser;
	isLoading: boolean;
	error: string;
}

type TUserSignUp = Omit<IUser, 'id' | 'role' | 'company'> & { password: string };

const initialState: UserState = {
	user: {
		id: 0,
		firstName: '',
		lastName: '',
		patronymic: '',
		email: '',
		role: 'user'
	},
	isLoading: false,
	error: ''
}

export const userSelectors = {
	getUser: (state: RootState) => state.userReducer.user,
	getIsAuth: (state: RootState) => !!state.userReducer.user.id,
}

export const userActions = {
	fetchUser: createAsyncThunk('user/fetch', async () => {
		const response = await axios.get<IUser>('https://62a51561259aba8e10f179f3.mockapi.io/user/1');
		return response.data;
	}),
	signUpUser: createAsyncThunk(
		'users/signupUser',
		async (data: TUserSignUp) => {
			const {firstName, lastName, patronymic, email, password} = data;
			const response = await axios.post<TUserSignUp>('https://62a51561259aba8e10f179f3.mockapi.io/',
			{
				firstName,
				lastName,
				patronymic,
				email,
				password,
			});
			
			return response.data;
		}
	  )
}

const userSlice = createSlice({
	name: 'user',
	initialState,
	reducers: {

	},
	extraReducers: {
		[userActions.fetchUser.pending.type]: (state) => {
			state.isLoading = true; 
		},
		[userActions.fetchUser.fulfilled.type]: (state, action: PayloadAction<IUser>) => {
			state.isLoading = false;
			state.error = '';
			state.user = action.payload;
		},
		[userActions.fetchUser.rejected.type]: (state, action: PayloadAction<string>) => {
			state.isLoading = false;
			state.error = action.payload;
		},
		// [userActions.signUpUser.pending.type]: (state, action: PayloadAction<string>) => {
		// 	state.isLoading = true;
		// },
		// [userActions.signUpUser.fulfilled.type]: (state, action: PayloadAction<{token: string}>) => {
		// 	localStorage.setItem('token', action.payload.token);
		// 	state.isLoading = false;
		// 	state.error = '';
		// }
	}	
})

export default userSlice.reducer;