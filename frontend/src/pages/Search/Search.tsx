import React, { FC } from 'react';
import { Header } from '../../components/Header';
import { Footer } from '../../components/Footer';
// import { DropDown } from './DropDown';

import './Search.scss';

export const Search: FC = () => {
    // const listCategories = ['', 'Наименование товара', 'Производитель', 'Категория', 'Цена', 'Описание'].map((category) =>
    //     <th className="search__category" key={category.toString()}>{category}</th>
    // )
    const listCategories = ['Наименование товара', 'Производитель', 'Категория', 'Цена', 'Описание'].map((category) =>
        <th className="search__category" key={category.toString()}>{category}</th>
    )

    const cellsInfo = [{
        name: 'Design',
        producer: 'Kek',
        category: 'Beb',
        price: '2400р',
        description: 'sadsadasdasdasdasdasd',
    }, {
        name: 'Design2',
        producer: 'Kek2',
        category: 'Beb2',
        price: '2500р',
        description: 'sadsadasdaseeqwewqedasdasdasd',
    }, {
        name: 'Design3',
        producer: 'Kek3',
        category: 'Beb3',
        price: '2600р',
        description: 'sadsadasdasdasdasfdsfdsfdasd',
    }];

    const cells = cellsInfo.map((cellInfo) =>
        <tr>
            {/*<td className="search__cell"><input type="checkbox"/></td>*/}
            <td className="search__cell">{cellInfo.name}</td>
            <td className="search__cell">{cellInfo.producer}</td>
            <td className="search__cell">{cellInfo.category}</td>
            <td className="search__cell">{cellInfo.price}</td>
            <td className="search__cell">{cellInfo.description}</td>
        </tr>
    );

    // const productFilter = ['А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё'];
    // const categoryFilter = ['А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё'];

    return (
        <>
            <Header />
                <main className="search search__background">
                <div className="search__container">
                    <div className="search__content">
                        <div className="search__search">
                            <div className="search__input-wrapper">
                                <input type="text" className="search__input" />
                            </div>
                            <button className="search__button">Найти</button>
                        </div >
                        <table className="search__table">
                            <tr className="search__categories">{listCategories}</tr>
                            {cells}
                        </table>
                        {/*<DropDown filterName={"Товар"} filterItems={productFilter}/>*/}
                        {/*<DropDown filterName={"Категория"} filterItems={categoryFilter}/>*/}
                        {/*<button className="search__filter__button">Применить</button>*/}
                        {/*<button className="search__filter__button">Сбросить фильтры</button>*/}
                    </div>
                </div>
            </main>
            <Footer />
        </>
    );
};
