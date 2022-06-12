import React, { FC } from 'react';
import { Header } from '../../components/Header';
import { Footer } from '../../components/Footer';

import './Home.scss';

export const Home: FC = () => {
    return (
        <>
            <Header />
            <main className="main main__background">
                <div className="main__container">
                    <div className="main__content">
                        <h2 className="main__title">Производители Москвы</h2>
                        <div className="main__search">
                            <div className="main__input-wrapper">
                                <input type="text" className="main__input" />
                            </div>
                            <button className="main__button">Найти</button>
                        </div>
                    </div>
                </div>
            </main>
			<Footer />
        </>
    );
};
