import React, { FC, useState } from 'react';
import { Header } from '../../components/Header';
import { Footer } from '../../components/Footer';
import { useSelector } from 'react-redux';
import { userSelectors } from '../../store/user';
import { Redirect } from 'react-router-dom';
import { LoginForm } from './components/LoginForm';
import { RegisterForm } from './components/RegisterForm';

import './Auth.scss';

export const Auth: FC = () => {
    const [showRegisterForm, setShowRegisterForm] = useState(false);

    const isAuth = useSelector(userSelectors.getIsAuth);

    if (isAuth) {
        return <Redirect to={'/profile'} />;
    }

    return (
        <>
            <Header />
            {showRegisterForm && (
                    <RegisterForm>
                        <button className='auth__button' onClick={() => setShowRegisterForm(false)}>
                            Вход
                        </button>
                    </RegisterForm>
            )}
            {!showRegisterForm && (
                    <LoginForm>
                        <button className='auth__button' onClick={() => setShowRegisterForm(true)}>
                            Регистрация
                        </button>
                    </LoginForm>
            )}
            <Footer />
        </>
    );
};
