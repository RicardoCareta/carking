import React, { useState } from "react";

import "./style.css";
import TakeOffVehicleParking from "../../services/takeOffVehicleParking";

export default function ParkingOutInformationPanel({
  name,
  plate,
  parkingPlace,
  brand,
  model,
  color,
  cpf,
  parkingId,
  price,
  callBackMap
}) {
  const parkingOutVehicleClickHandler = async () => {
    const result = await TakeOffVehicleParking(parkingId);
    if (result) {
      alert(
        "Veículo adicionado na fila para retirar.\nO funcionário " +
          result.driver.name +
          " trará o veículo."
      );

      callBackMap();
    } else {
      alert("Ocorreu um erro");
    }
  };

  return (
    <form id="parking-out-information-form">
      <input
        type="text"
        id="name-field"
        placeholder="Proprietário"
        value={name}
        disabled
      />
      <input
        disabled
        type="text"
        id="cpf-field"
        placeholder="CPF"
        value={cpf}
      />

      <div className="row-parking-out-information">
        <input
          disabled
          type="text"
          id="brand-field"
          placeholder="Marca"
          value={brand}
        />

        <input
          disabled
          type="text"
          id="model-field"
          placeholder="Modelo"
          value={model}
        />
      </div>

      <div className="row-parking-out-information">
        <input
          disabled
          type="text"
          id="color-field"
          placeholder="Cor"
          value={color}
        />
        <input
          disabled
          type="text"
          id="plate-field"
          placeholder="Placa"
          value={plate}
        />
      </div>
      <div className="row-parking-out-information">
        <input
          disabled
          type="button"
          style={{
            justifyContent: "center"
          }}
          value={parkingPlace}
        />
        <input
          disabled
          type="text"
          id="price-field"
          placeholder="Preço"
          value={price}
        />
      </div>

      <div id="button-div-parking-out-information">
        <input
          type="button"
          value="RETIRAR VEÍCULO"
          id="btn-in"
          onClick={parkingOutVehicleClickHandler}
        />
      </div>
    </form>
  );
}
