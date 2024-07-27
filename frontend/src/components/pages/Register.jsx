import { Link } from "react-router-dom";
import { useState } from "react";
export const Register = () => {
    const [register,setRegister] = useState({username:'', password:'', email: '', userType: {
        id: 1,
        userType: "ADMIN"
    }, createdAt:"1502713067.720000000"})
    const inputHandler = (event) => {
        setRegister({...register, [event.target.name]: event.target.value})
    }
    
    return (
      <div>
        <form>
                <label>Username </label>
                <input type='text' name='username' value={register.username} onChange={inputHandler} />
                <label>Password </label>
                <input type='password' name='password' value={register.password} onChange={inputHandler} />
                <label>Email </label>
                <input type='text' name='email' value={register.email} onChange={inputHandler} />
                <button type="submit">Register</button><br></br><br></br>
                <Link to="/login">Login</Link>
            </form>
      </div>
    )
  }