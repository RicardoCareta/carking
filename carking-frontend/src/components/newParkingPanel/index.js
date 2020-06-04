import React, { useState, useEffect } from "react";
import "./style.css";

import getNoBusyEmployee from "../../services/getNoBusyEmployee";
import registerParking from "../../services/registerParking";
import ParkingMapPanel from "../parkingMapPanel";

import MaskedInput from "react-text-mask";

const maskCellphone = [
  "(",
  /\d/,
  /\d/,
  ")",
  " ",
  /\d/,
  /\d/,
  /\d/,
  /\d/,
  /\d/,
  "-",
  /\d/,
  /\d/,
  /\d/,
  /\d/
];

function NewParkingPanel() {
  const [name, setName] = useState("");
  const [cpf, setCPF] = useState("");
  const [telephone, setTelephone] = useState("");
  const [telephone2, setTelephone2] = useState("");
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [color, setColor] = useState("");
  const [plate, setPlate] = useState("");
  const [parkingPlace, setParkingPlace] = useState("Escolha a vaga");
  const [driver, setDriver] = useState("");
  const [drivers, setDrivers] = useState([]);

  const [parkingPlacePanel, setParkingPlacePanel] = useState(false);

  const loadDrivers = async () => {
    try {
      setDrivers(await getNoBusyEmployee());
    } catch (err) {
      console.log(err);
    }
  };

  useEffect(() => {
    loadDrivers();
  }, []);

  const showParkingPlaceMap = () => {
    setParkingPlacePanel(true);
  };

  const callBackParkingMapSelect = (vaga, id) => {
    setParkingPlace(vaga);
    setParkingPlacePanel(false);
  };

  const saveParkingHandler = async () => {
    try {
      const attendent = await localStorage.getItem("user");
      const result = await registerParking(
        parkingPlace,
        driver,
        attendent,
        name,
        cpf,
        telephone,
        model,
        plate,
        brand,
        color,
        telephone2
      );

      if (result) {
        alert("Veículo salvo com sucesso");

        setName("");
        setCPF("");
        setTelephone("");
        setTelephone2("");
        setBrand("");
        setModel("");
        setColor("");
        setPlate("");
        setParkingPlace("Escolha a vaga");
        setDriver("");
        setTelephone2("");
      }
    } catch (err) {
      alert("Não foi possível adicionar o veículo");
    }
  };

  return (
    <div id="newparking-panel">
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
      <div id="newparking-form">
        {parkingPlacePanel && (
          <ParkingMapPanel
            callBackParkingMapSelect={callBackParkingMapSelect}
          />
        )}
        {!parkingPlacePanel && (
          <form id="new-parking">
            <input
              type="text"
              id="name-field"
              placeholder="Proprietário"
              value={name}
              onChange={ev => setName(ev.target.value)}
            />
            <MaskedInput
              mask={[
                /\d/,
                /\d/,
                /\d/,
                ".",
                /\d/,
                /\d/,
                /\d/,
                ".",
                /\d/,
                /\d/,
                /\d/,
                "-",
                /\d/,
                /\d/
              ]}
              type="text"
              id="cpf-field"
              placeholder="CPF"
              value={cpf}
              onChange={ev => setCPF(ev.target.value)}
            />

            <div className="row-new-parking">
              <MaskedInput
                mask={maskCellphone}
                type="text"
                id="telephone-field"
                placeholder="Telefone"
                value={telephone}
                onChange={ev => setTelephone(ev.target.value)}
              />

              <MaskedInput
                mask={maskCellphone}
                type="text"
                id="telephone2-field"
                placeholder="Telefone2"
                value={telephone2}
                onChange={ev => setTelephone2(ev.target.value)}
              />
            </div>

            <div className="row-new-parking">
              <input
                type="text"
                id="brand-field"
                placeholder="Marca"
                value={brand}
                onChange={ev => setBrand(ev.target.value)}
              />

              <input
                type="text"
                id="model-field"
                placeholder="Modelo"
                value={model}
                onChange={ev => setModel(ev.target.value)}
              />
            </div>

            <div className="row-new-parking">
              <input
                type="text"
                id="color-field"
                placeholder="Cor"
                value={color}
                onChange={ev => setColor(ev.target.value)}
              />

              {/* <input type="button" value="Escolha a placa" /> */}
              <input
                type="text"
                id="plate-field"
                placeholder="Placa"
                value={plate}
                onChange={ev => setPlate(ev.target.value)}
              />
            </div>

            <div className="row-new-parking">
              <input
                type="button"
                style={{
                  justifyContent: "center"
                }}
                value={parkingPlace}
                onClick={showParkingPlaceMap}
              />
              {/* <input
              type="text"
              id="parkingPlace-field"
              placeholder="Vaga"
              value={parkingPlace}
              onChange={ev => setParkingPlace(ev.target.value)}
            /> */}

              <select
                value={driver}
                onChange={ev => setDriver(ev.target.value)}
              >
                <option value="">Selecione</option>
                {drivers.map(drive => (
                  <option key={drive.id} value={drive.id}>
                    {drive.name}
                  </option>
                ))}
              </select>
            </div>

            <div id="button-div-save-parking">
              <input
                type="button"
                value="SALVAR"
                id="btn-in"
                onClick={saveParkingHandler}
              />
            </div>
          </form>
        )}
      </div>
    </div>
  );
}

export default NewParkingPanel;
