import React, { useState } from "react";
import "./style.css";

import registerEmployee from "../../services/registerEmployee";

function NewEmployeePanel() {
  const [name, setName] = useState("");
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("");

  const saveEmployeeHandler = async () => {
    try {
      const result = await registerEmployee(
        name,
        username,
        password,
        "123.456.789-09",
        "12345678909",
        role
      );
      if (result && result !== -1) {
        alert("Funcionário salvo com sucesso");
        setName("");
        setPassword("");
        setRole("");
        setUsername("");
      } else if (result === -1) {
        alert("Usuário informado já existe");
      }
    } catch (err) {
      alert("Não foi possível adicionar o funcionário");
    }
  };

  return (
    <div id="newemployee-panel">
      <link
        href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap"
        rel="stylesheet"
      />
      <img
        src="https://i.imgur.com/LuR0fL1.png"
        alt="Logo"
        title="Carkin' Logo"
        id="logo"
      />
      <div id="neweployee-form">
        <form id="new-employee">
          <input
            type="text"
            id="name-field"
            placeholder="Nome"
            value={name}
            onChange={ev => setName(ev.target.value)}
          />
          <input
            type="text"
            id="user-field"
            placeholder="Usuário"
            value={username}
            onChange={ev => setUsername(ev.target.value)}
          />
          <input
            type="password"
            id="pass-field"
            placeholder="Senha"
            value={password}
            onChange={ev => setPassword(ev.target.value)}
          />

          <select value={role} onChange={ev => setRole(ev.target.value)}>
            <option value="">Selecione</option>
            <option value="attendant">Atendente</option>
            <option value="manager">Gerente</option>
            <option value="driver">Motorista</option>
          </select>

          <div id="button-div-save-employee">
            <input
              type="button"
              value="SALVAR"
              id="btn-in"
              onClick={saveEmployeeHandler}
            />
          </div>
        </form>
      </div>
    </div>
  );
}

export default NewEmployeePanel;
