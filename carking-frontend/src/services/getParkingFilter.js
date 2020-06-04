import api from "./api";

export default async function getParkingFilter(parkingPlace) {
  try {
    let filter = "/parking/filter?";
    if (parkingPlace) {
      filter += "parkingPlace=" + parkingPlace;
    }

    const result = await api().get(filter);
    return result.data;
  } catch (err) {
    console.log(err);
    return null;
  }
}
