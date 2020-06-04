import React, { useState, useEffect } from "react";
import "./style.css";

import ParkingMapPanel from "../parkingMapPanel";
import getParkingFilter from "../../services/getParkingFilter";
import ParkingOutInformationPanel from "../parkingOutInformationPanel";

function ParkingOutPanel() {
  const [name, setName] = useState("");
  const [plate, setPlate] = useState("");
  const [parkingPlace, setParkingPlace] = useState("");
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [color, setColor] = useState("");
  const [cpf, setCPF] = useState("");
  const [price, setPrice] = useState("");
  const [parkingId, setParkingId] = useState("");

  const [showInformation, setShowInformation] = useState(false);

  const callBackParkingMapSelect = async (parkingPlace, id) => {
    const result = await getParkingFilter(parkingPlace);
    if (result) {
      setName(result.customerDTO.name);
      setPlate(result.vehicle.plate);
      setParkingPlace(result.parkingPlace);
      setBrand(result.vehicle.brand);
      setModel(result.vehicle.model);
      setColor(result.vehicle.color);
      setCPF(result.customerDTO.cpf);
      setPrice(result.price);
      setParkingId(result.id);

      setShowInformation(true);
    } else {
      alert("Ocorreu um erro");
    }
  };

  const callBackMap = () => {
    setShowInformation(false);
  };

  return (
    <div id="parking-out-panel">
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
      <div id="parking-out-form">
        {!showInformation && (
          <form id="parking-out">
            <div>
              <ParkingMapPanel
                callBackParkingMapSelect={callBackParkingMapSelect}
                isOut={true}
              />
            </div>
          </form>
        )}
        {showInformation && (
          <ParkingOutInformationPanel
            brand={brand}
            name={name}
            plate={plate}
            parkingPlace={parkingPlace}
            model={model}
            color={color}
            cpf={cpf}
            price={price}
            parkingId={parkingId}
            callBackMap={callBackMap}
          />
        )}
      </div>
    </div>
  );
}

export default ParkingOutPanel;
