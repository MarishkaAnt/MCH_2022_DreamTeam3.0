import { TCompany } from './../types';

type TUserRole = "user" | "admin" | "company";

export interface IUser { 
	id: number;
	firstName: string;
	lastName: string;
	patronymic: string;
	email: string;
	role: {
		name: TUserRole;
	};
	company?: TCompany;
}