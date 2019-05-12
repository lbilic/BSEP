export class Software {
  alias: string;
  isConnected: Boolean;

  constructor(software: string, isConnected: boolean ) {
    this.alias = software;
    this.isConnected = isConnected;
  }
}