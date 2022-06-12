import { combineReducers, configureStore } from "@reduxjs/toolkit";
import userReducer from './user';

const rootReducer = combineReducers({
	userReducer
});

export const setupStore = () => {
	return configureStore({
		reducer: rootReducer
	}) 
}

export type RootState = ReturnType<typeof rootReducer>;
