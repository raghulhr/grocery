import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import './App.css';
import { Home } from './components/pages/Home';
import { Login } from './components/pages/Login';
import { Register } from './components/pages/Register';
import { ProductInfo } from './components/products/ProductInfo';

function App() {
  

  return (
    <>
      <Router>
        <Routes>
          <Route exact path='/' element={<Home/> }></Route>
          <Route path='/login'element={<Login/> }></Route>
          <Route path='/register'element={<Register/> }></Route>
          <Route path='/product/:id'element={<ProductInfo /> }></Route>
        </Routes>
      </Router>
    </>
  )
}

export default App
