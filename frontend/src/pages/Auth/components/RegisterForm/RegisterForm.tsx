import { ErrorMessage, Field, Form, Formik } from 'formik';
import { FC } from 'react';
import { useDispatch } from 'react-redux';
import { userActions } from '../../../../store/user';
import { signUpValidationSchema as validationSchema } from '../../utils';

import "./RegisterForm.scss";

export const RegisterForm: FC = ({children}) => {
    const dispatch = useDispatch();

    interface IInitialValues {
        email: string;
        password: string;
        firstName: string;
        lastName: string;
        patronymic: string;
    }

    const initialValues = {
        email: '',
        password: '',
        firstName: '',
        lastName: '',
        patronymic: '',
    };

    const onSubmit = (values: IInitialValues) => {
        dispatch(userActions.signUpUser(values));
    };

    const renderError = (message: string) => <p className="register-form__error">{message}</p>;

    return (
        <div className="register-form">
            <div className="register-form__container">
                <div className="register-form__content">
                    <Formik
                        initialValues={initialValues}
                        validationSchema={validationSchema}
                        onSubmit={onSubmit}
                        noValidate
                    >
                        <Form className="register-form__form">
                            <h2 className="register-form__title">Вход</h2>
                            <label className="register-form__input-wrapper" htmlFor="email">
                                <p className="register-form__label">Почта</p>
                                <Field name="email" type="text" className="register-form__input" />
                                <ErrorMessage name="email" render={renderError} />
                            </label>
                            <label className="register-form__input-wrapper" htmlFor="firstName">
                                <p className="register-form__label">Имя</p>
                                <Field name="firstName" type="text" className="register-form__input" />
                                <ErrorMessage name="firstName" render={renderError} />
                            </label>
                            <label className="register-form__input-wrapper" htmlFor="lastName">
                                <p className="register-form__label">Фамилия</p>
                                <Field name="lastName" type="text" className="register-form__input" />
                                <ErrorMessage name="lastName" render={renderError} />
                            </label>
                            <label className="register-form__input-wrapper" htmlFor="patronymic">
                                <p className="register-form__label">Отчество</p>
                                <Field name="patronymic" type="text" className="register-form__input" />
                                <ErrorMessage name="patronymic" render={renderError} />
                            </label>
                            <label className="register-form__input-wrapper" htmlFor="password">
                                <p className="register-form__label">Пароль</p>
                                <Field name="password" type="password" className="register-form__input" />
                                <ErrorMessage name="password" render={renderError} />
                            </label>
                            <button type="submit" className="register-form__button">
                                Войти
                            </button>
                            {children}
                        </Form>
                    </Formik>
                </div>
            </div>
        </div>
    );
};
