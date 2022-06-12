import React, { FC } from 'react';
import { Formik, Form, Field, ErrorMessage } from 'formik';
import { Header } from '../../components/Header';
import { Footer } from '../../components/Footer';
import {
    useSelector,
    useDispatch,
} from 'react-redux';
import {
    userSelectors,
    userActions,
} from '../../store/user';
import { Redirect } from 'react-router-dom';
import { validationSchema } from './utils';

import './Auth.scss';

export const Auth: FC = () => {
    const isAuth = useSelector(userSelectors.getIsAuth);
    const dispatch = useDispatch();

    if (isAuth) {
        return <Redirect to={'/profile'} />;
    }

    interface IInitialValues {
        email: string;
        password: string;
    }

    const initialValues = {
        email: '',
        password: '',
    };

    const onSubmit = (values: IInitialValues) => {
		dispatch(userActions.loginUser(values))
    };

    const renderError = (message: string) => <p className="auth__error">{message}</p>;

    return (
        <>
            <Header />
            <div className="auth">
                <div className="auth__container">
                    <div className="auth__content">
                        <Formik
                            initialValues={initialValues}
                            validationSchema={validationSchema}
                            onSubmit={onSubmit}
                            noValidate
                        >
                            <Form className="auth__form">
                                <h2 className="auth__title">Вход</h2>
                                <label className='auth__input-wrapper' htmlFor="email">
									<p className='auth__label'>Почта</p>
                                    <Field name="email" type="text" className="auth__input" />
                                    <ErrorMessage name="email" render={renderError} />
                                </label>
                                <label className='auth__input-wrapper' htmlFor="password">
									<p className='auth__label'>Пароль</p>
                                    <Field name="password" type="password" className="auth__input" />
									<ErrorMessage name="password" render={renderError} />
                                </label>
                                <button type="submit" className="auth__button">
                                    Войти
                                </button>
                            </Form>
                        </Formik>
                    </div>
                </div>
            </div>
            <Footer />
        </>
    );
};
