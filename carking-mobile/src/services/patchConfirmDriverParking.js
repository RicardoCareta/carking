import api from "./api";

export default async function patchConfirmDriverParking(
  confirmType,
  parkingId
) {
  try {
    const result = await api().patch("/driver/confirm", {
      confirmType,
      parkingId
    });

    return result.data;
  } catch (err) {
    console.log(err);
    return null;
  }
}
