import api from "./api";

export default async function getTakeOffDriverParking() {
  try {
    const result = await api().get("/driver/takeoff");
    return result.data;
  } catch (err) {
    console.log(err);
    return [];
  }
}
