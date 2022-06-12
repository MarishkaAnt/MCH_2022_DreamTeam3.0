type TUserRole = "user" | "admin" | "company";

type TUserCompany = {
	id: number;
	name: string;
	inn: string;
	okved: string;
	address: string;
	url: string;
}

export interface IUser { 
	id: number;
	firstName: string;
	lastName: string;
	patronymic: string;
	email: string;
	role: TUserRole;
	company?: TUserCompany;
}