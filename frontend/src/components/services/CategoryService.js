import axios from 'axios';

// const Base_URL = 'http://localhost:8080/categories';
const Base_URL = 'https://fakestoreapi.com/products/categories';

export class CategoryService{
    static getAllCategoriess(){
        return axios.get(`${Base_URL}`);
    }
    
}