import api from "./api";

export default async function TakeOffVehicleParking(parkingId) {
  try {
    const result = await api().patch("/parking/" + parkingId);
    return result.data;
  } catch (err) {
    console.log(err);
    return null;
  }
}
