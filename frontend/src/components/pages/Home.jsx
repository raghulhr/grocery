import { useState } from 'react'

import { CategoryList } from '../categories/CategoryList'
import { NavigationBar } from '../navigation/NavigationBar'
import { ProductList } from '../products/ProductList'

export const Home = () => {
  const [searchValue, setSearchValue] = useState("");
  const searchValueCallBack = (value) => {
    setSearchValue(value);
  }
  return (
    <div>
      <NavigationBar NavigationBarSearchValueCallBack={searchValueCallBack}/>
      <CategoryList />
      <ProductList searchValue={searchValue}/>
    </div>
  )
}