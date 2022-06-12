import { object, string } from 'yup';

const messageRequired = 'Это поле обязательно для заполнения';
const messageError = 'Неправильно заполнено поле';
const passwordError =
    'Пароль должен содержать 8 символов, 1 символ верхнего регистра, 1 нижнего, 1 цифру и 1 специальный символ';

export const loginValidationSchema = object().shape({
    email: string().required(messageRequired).email(messageError),
    password: string()
        .required(messageRequired)
        .matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#%&])(?=.{8,})/, passwordError),
});

export const signUpValidationSchema = object().shape({
    email: string().required(messageRequired).email(messageError),
    password: string()
        .required(messageRequired)
        .matches(/^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#%&])(?=.{8,})/, passwordError),
    firstName: string().required(messageRequired),
    lastName: string().required(messageRequired),
    patronymic: string().required(messageRequired),
});
