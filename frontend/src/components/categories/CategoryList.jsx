import React from 'react'
import { useState, useEffect } from 'react'
import { CategoryService } from '../services/CategoryService'
import './CategoryList.scss'
import { CategoryCard } from './CategoryCard'

export const CategoryList = () => {
    const [categories, setCategories] = useState([])
    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const res = await CategoryService.getAllCategories();
                const data = await res.data;
                setCategories(data);
            } catch (error) {
                alert(error);
            }
        }
        fetchCategories();
    }, []);
    return (
        <div className='category-list'>
            <CategoryCard title={'All'} image={'https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Ftse1.mm.bing.net%2Fth%3Fid%3DOIP.L0wWBfTcwEfK5q4BKveN9QHaGl%26pid%3DApi&f=1&ipt=03649ccd785b18951f54e4e7ad830c79a165884b30067b032ed05ee044013cb3&ipo=images'}/>
            {
                categories.map((item, index) => {
                    return(
                        <CategoryCard key={index}/>
                    )
                })
            }
        </div>
    )
}