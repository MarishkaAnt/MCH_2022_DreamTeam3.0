import React, {FC, useState} from 'react';

import './DropDown.scss';

interface DropDownProps {
    filterName: string;
    filterItems: string[];
}

export const DropDown: FC<DropDownProps> = (props) => {
    const [isOpened, setIsOpened] = useState(false);

    const initFilterStates = props.filterItems.map((item) => ({ title: item, checked: false }));
    const [checkboxStateArray, setCheckboxStateArray] = useState(initFilterStates);

    const filterItems = initFilterStates.map((item) =>
        <div className="dropdown__filter"><label><input type="checkbox" onChange={(event) => checkboxHandle(item.title)}/>{item.title}</label></div>
    );

    function dropDownMenu() {
        setIsOpened(!isOpened);
    }

    function checkboxHandle(filterTitle: string) {
        const newOldCheckboxStateArray = checkboxStateArray.map(item => {
            return (item.title === filterTitle) ? {
                ...item,
                checked: !item.checked,
            } : item;
        });
        setCheckboxStateArray(newOldCheckboxStateArray);
    }


    return (
        <>
            <div className="dropdown">
                <div className={`dropdown__container_${isOpened ? 'opened' : 'closed'}`} onClick={() => dropDownMenu()}>
                    {props.filterName}
                    {!isOpened && filterItems}
                </div>
            </div>
        </>
    );
};
