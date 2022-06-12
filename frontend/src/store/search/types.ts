import { TCompany } from './../types';

export interface ISearch {
	name: string,
	page: number,
	size: number,
}

export type TProduct = {
	id: number;
	name: string;
	description: string;
	price: number;
	company: TCompany;
}