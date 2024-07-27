import axios from 'axios';

// const Base_URL = 'http://localhost:8080/product';
const Base_URL = 'https://fakestoreapi.com/products';

export class ProductService{
    static getAllProducts(){
        return axios.get(`${Base_URL}`);
    }
    static getProductById(id){
        return axios.get(`${Base_URL}/${id}`);
    }
    
}