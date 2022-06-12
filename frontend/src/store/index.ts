import { combineReducers, configureStore } from "@reduxjs/toolkit";
import userReducer from './user';
import searchReducer from './search'

const rootReducer = combineReducers({
	userReducer,
	searchReducer,
});

export const setupStore = () => {
	return configureStore({
		reducer: rootReducer
	}) 
}

export type RootState = ReturnType<typeof rootReducer>;
