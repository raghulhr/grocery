import React from 'react'

import './CategoryCard.scss'

export const CategoryCard = ({title, image}) => {
  return (
    <div className='category-card-container'>
        <div className='category-image-container'>
                <img src={image} alt={'category'} />
            </div>
            <div className='category-card-details'>
                <div className='category-card-title'>
                    {title}
                </div>
            </div>
    </div>
  )
}