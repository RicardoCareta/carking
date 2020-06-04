import React, { useState } from "react";
import "./style.css";

import login from "../../services/login";
import { useHistory } from "react-router-dom";

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const history = useHistory();

  const loginClickHandler = async () => {
    const result = await login(username, password);
    if (result) {
      history.push("/main");
    } else {
      alert("Usuário informado é inválido");
    }
  };
  return (
    <main>
      <link
        href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap"
        rel="stylesheet"
      />
      <header>
        <h1 id="logotxt">Driver App</h1>
      </header>
      <section id="container-preto">
        <div id="container-logo">
          <img id="logo" src="https://i.imgur.com/Z7fOriF.png" alt="logo" />
        </div>

        <div id="login-form">
          <form id="login">
            <input
              value={username}
              onChange={e => setUsername(e.target.value)}
              type="text"
              id="user-field"
              placeholder="Usuário"
            />
            <input
              value={password}
              onChange={e => setPassword(e.target.value)}
              type="password"
              id="pass-field"
              placeholder="Senha"
            />
            <input
              type="button"
              value="ENTRAR"
              id="btn-in"
              onClick={loginClickHandler}
            />
          </form>
        </div>
        <p>©Pastelaria de Software | 2020</p>
      </section>
    </main>
  );
}
