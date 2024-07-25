import React, { useEffect, useState } from 'react'
import { CategoryService } from '../services/CategoryService';

export const ProductCategories = async () => {
  const [categories, setCategories] = useState([]);
  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const res = await CategoryService.getAllProducts();
        const data = await res.data;
        setCategories(data);
      } catch (error) {
        alert(error) { }
      }
    }
    fetchCategories();
  })
  return (
    <div>
      <div className="category-list">
        {
          categories.map((item, index) => {
            return (
              <Link key={index} to={`/categories/${item.id}`} ><ProductCard key={index} product={item} /></Link>
            )
          })
        }
      </div>
    </div>
  )
}
