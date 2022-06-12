import React, { FC, useCallback, useEffect, useState } from 'react';
import { Header } from '../../components/Header';
import { Footer } from '../../components/Footer';
import { searcActions, searchSelectors } from '../../store/search';
import { useDispatch, useSelector } from 'react-redux';

import './Search.scss';

export const Search: FC = () => {
    const [searchValue, setSearchValue] = useState('');

    const dispatch = useDispatch();
    const products = useSelector(searchSelectors.getProducts);
    const isSearchLoading = useSelector(searchSelectors.getIsLoading);

    const searchHandler = useCallback(() => {
        if (!searchValue) {
            return;
        }

        dispatch(
            searcActions.search({
                name: searchValue,
                page: 1,
                size: 100,
            }),
        );
    }, [dispatch, searchValue]);

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

    const listCategories = ['Наименование товара', 'Производитель', 'Сайт', 'Цена', 'Описание'].map((category) =>
        <th className="search__category" key={category.toString()}>{category}</th>
    )

    const cells = products.map((cellInfo) =>
        <tr>
            <td className="search__cell">{cellInfo.name}</td>
            <td className="search__cell">{cellInfo.company.name}</td>
            <td className="search__cell">{cellInfo.company.url}</td>
            <td className="search__cell">{cellInfo.price}</td>
            <td className="search__cell">{cellInfo.description}</td>
        </tr>
    );

    return (
        <>
            <Header />
                <main className="search search__background">
                <div className="search__container">
                    <div className="search__content">
                        <div className="search__search">
                            <div className="search__input-wrapper">
                                <input type="text" className="search__input" value={searchValue} onChange={(e) => setSearchValue(e.target.value)} />
                            </div>
                            <button className="search__button" onClick={searchHandler}>Найти</button>
                        </div >
                        {isSearchLoading && <p>Идет загрузка...</p>}
                        <table className="search__table">
                            <tr className="search__categories">{listCategories}</tr>
                            {cells}
                        </table>
                    </div>
                </div>
            </main>
            <Footer />
        </>
    );
};
