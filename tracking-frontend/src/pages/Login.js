import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Login() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const navigate = useNavigate();

    const handleLogin = async () => {

        try {

            const response = await fetch(
                'http://localhost:8081/auth/login',
                {
                    method: 'POST',
                    headers: {
                        'Content-Type':
                            'application/json'
                    },
                    body: JSON.stringify({
                        username,
                        password
                    })
                }
            );

            const data =
                await response.json();

            if (data.token) {

                localStorage.setItem(
                    'token',
                    data.token
                );

                navigate('/dashboard');

            } else {

                alert('Invalid Credentials');
            }

        } catch (error) {

            console.error(error);

            alert('Login Failed');
        }
    };

    return (

        <div className="login-container">

            <div className="login-card">

                <h1>🚚 Logistics Login</h1>

                <input
                    type="text"
                    placeholder="Username"
                    value={username}
                    onChange={(e) =>
                        setUsername(
                            e.target.value
                        )
                    }
                />

                <input
                    type="password"
                    placeholder="Password"
                    value={password}
                    onChange={(e) =>
                        setPassword(
                            e.target.value
                        )
                    }
                />

                <button
                    onClick={handleLogin}
                >
                    Login
                </button>

            </div>

        </div>
    );
}

export default Login;