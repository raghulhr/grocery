import React, { useState } from "react";
import { Link } from "react-router-dom";
import Logo from '../../assets/logo/Logo.png'
import './NavigationBar.scss'

export function NavigationBar({ NavigationBarSearchValueCallBack }) {

  const [searchValue, setSearchValue] = useState("");

  const handleInputChange = (event) => {
    setSearchValue(event.target.value);
    if (event.target.value === "") {
      NavigationBarSearchValueCallBack("");
    }
  }

  const handleButtonClick = () => {
    if (searchValue !== "") {
      NavigationBarSearchValueCallBack(searchValue);
    }
  }

  return (
    <nav className="nav">
      <div className="site-title-card">
        <img src={Logo} alt="alaigal.org" className="image" />
        <a href="/" className="site-title">Grocery Application</a>
      </div>
      <div className="nav-list">
        <ul>
          <div className="cart-nav">
            <li><Link to="/song">Cart</Link></li>
          </div>
          <div className="orders-nav">
            <li><Link to="/feed">Orders</Link></li>
          </div>
          <div className="search-nav">
            <Link to='/'><li><input placeholder="Search..." value={searchValue} onChange={handleInputChange} />
              <button onClick={handleButtonClick} disabled={searchValue.length < 3}>Search</button></li>
            </Link>
          </div>
        </ul>
      </div>
    </nav>
  );
}
