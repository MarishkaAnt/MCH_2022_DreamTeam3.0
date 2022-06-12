import React, { FC, useCallback, useEffect, useState } from 'react';
import { Header } from '../../components/Header';
import { Footer } from '../../components/Footer';
import { searcActions, searchSelectors } from '../../store/search';
import { useDispatch, useSelector } from 'react-redux';
import { useHistory } from 'react-router-dom';

import './Home.scss';

export const Home: FC = () => {
    const history = useHistory();

    const dispatch = useDispatch();
    const [searchValue, setSearchValue] = useState('');
    const isSearchLoading = useSelector(searchSelectors.getIsLoading);
    const products = useSelector(searchSelectors.getProducts);

    const searchHandler = useCallback(() => {
        if (!searchValue) {
            return;
        }

        dispatch(
            searcActions.search({
                name: searchValue,
                page: 0,
                size: 100,
            }),
        );
    }, [dispatch, searchValue]);

    useEffect(() => {
        if (isSearchLoading) {
            return;
        }

        if (products.length) {
            history.push('/search/0');
        }
    }, [isSearchLoading, products, history]);

    useEffect(() => {
        const listener = (event: KeyboardEvent) => {
            if (event.code === 'Enter' || event.code === 'NumpadEnter') {
                event.preventDefault();
                searchHandler();
            }
        };
        document.addEventListener('keydown', listener);
        return () => {
            document.removeEventListener('keydown', listener);
        };
    }, [searchHandler]);

    return (
        <>
            <Header />
            <main className="main main__background">
                <div className="main__container">
                    <div className="main__content">
                        <h2 className="main__title">Производители Москвы</h2>
                        <div className="main__search">
                            <div className="main__input-wrapper">
                                <input
                                    type="text"
                                    className="main__input"
                                    value={searchValue}
                                    onChange={(e) => setSearchValue(e.target.value)}
                                />
                            </div>
                            <button className="main__button" onClick={searchHandler}>
                                Найти
                            </button>
                        </div>
                        {isSearchLoading && <p>Идет загрузка...</p>}
                    </div>
                </div>
            </main>
            <Footer />
        </>
    );
};
