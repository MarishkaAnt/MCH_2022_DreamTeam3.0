import { ErrorMessage, Field, Form, Formik } from 'formik';
import { FC } from 'react';
import { useDispatch } from 'react-redux';
import { userActions } from '../../../../store/user';
import { loginValidationSchema as validationSchema } from '../../utils';

import './LoginForm.scss';

export const LoginForm: FC = ({children}) => {
	const dispatch = useDispatch();

	interface IInitialValues {
        email: string;
        password: string;
    }

    const initialValues = {
        email: '',
        password: '',
    };

	const onSubmit = (values: IInitialValues) => {
        dispatch(userActions.loginUser(values));
    };

	const renderError = (message: string) => <p className="login-form__error">{message}</p>;

    return (
        <div className="login-form">
            <div className="login-form__container">
                <div className="login-form__content">
                    <Formik
                        initialValues={initialValues}
                        validationSchema={validationSchema}
                        onSubmit={onSubmit}
                        noValidate
                    >
                        <Form className="login-form__form">
                            <h2 className="login-form__title">Вход</h2>
                            <label className="login-form__input-wrapper" htmlFor="email">
                                <p className="login-form__label">Почта</p>
                                <Field name="email" type="text" className="login-form__input" />
                                <ErrorMessage name="email" render={renderError} />
                            </label>
                            <label className="login-form__input-wrapper" htmlFor="password">
                                <p className="login-form__label">Пароль</p>
                                <Field name="password" type="password" className="login-form__input" />
                                <ErrorMessage name="password" render={renderError} />
                            </label>
                            <button type="submit" className="login-form__button">
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
