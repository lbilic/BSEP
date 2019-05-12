export class Connected {
  connectedWith: [];
  others: [];

  constructor(public software) {
    this.connectedWith = software.connectedWith;
    this.others = software.connectedWith;
  }
}