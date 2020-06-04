import api from "./api";

export default async function registerParking(
  parkingPlace,
  driver,
  attendant,
  name,
  cpf,
  cellNumber,
  model,
  plate,
  brand,
  color,
  telephone
) {
  try {
    const response = await api().post("/parking", {
      parkingPlace,
      driver,
      attendant,
      customer: {
        name,
        cpf,
        cellNumber,
        telephone
      },
      vehicle: {
        model,
        plate,
        brand,
        color
      }
    });
    console.log(response.data);
    return response.data.id;
  } catch (err) {
    console.log(err.response);
    if (err.response.status === 400) {
      console.log(err.response.data.error);
    }
  }
}
