import React, { FC } from 'react';
import { Link } from 'react-router-dom';

import './Footer.scss';

export const Footer: FC = () => {
  return (
    <footer className="footer">
      <div className="footer__container">
        <div className="footer__content">
          <Link to={'/'} className="profile__link">
            <div className="footer__logo">
              <p>Название сайта</p>
            </div>
          </Link>
        </div>
      </div>
    </footer>
  );
};
