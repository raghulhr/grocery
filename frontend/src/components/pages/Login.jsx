import { Link } from "react-router-dom";
import { useState } from "react";
export const Login = () => {
    const [login, setLogin] = useState({username:'', password:''})
    const inputHandler = (event) => {
        setLogin({...login, [event.target.name]: event.target.value})
    }
    return (
        <div>
            <form>
                <label>Username </label>
                <input type='text' name='username' value={login.username} onChange={inputHandler} />
                <label>Password </label>
                <input type='text' name='password' value={login.password} onChange={inputHandler} />
                <button type="submit">Login</button><br></br><br></br>
                <Link to="/register" >Register</Link>
            </form>
        </div>
    )
}