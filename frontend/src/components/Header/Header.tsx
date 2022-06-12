import React, { FC, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import account from './../../icons/account.svg';
import { userSelectors, userActions } from '../../store/user';

import './Header.scss';

export const Header: FC = () => {
  const isAuth = useSelector(userSelectors.getIsAuth);
  const dispatch = useDispatch();

  useEffect(() => {
    dispatch(userActions.fetchUser());
  }, [dispatch]);

  return (
    <header className="header">
      <div className="header__container">
        <div className="header__content">
          <Link to={'/'} className="profile__link">
            <div className="header__logo">
              <p>Моспром Импортозамещение</p>
            </div>
          </Link>
          <div className="header__profile profile">
            {isAuth ? (
              <Link to={'/profile'} className="profile__link">
                <div className="profile__logged">
                  <div className="profile__icon">
                    <img src={account} alt="Профиль" />
                  </div>
                  <div className="profile__text">
                    <p>Профиль</p>
                  </div>
                </div>
              </Link>
            ) : (
              <Link to={'/login'} className="profile__link">
                Вход
              </Link>
            )}
          </div>
        </div>
      </div>
    </header>
  );
};
