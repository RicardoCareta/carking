import api from "./api";

export default async function() {
  try {
    const response = await api().get("/driver/no-busy");
    return response.data;
  } catch (err) {
    console.log(err);
  }
}
