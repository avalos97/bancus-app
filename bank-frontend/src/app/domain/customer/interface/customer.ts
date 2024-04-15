import { Account } from "./account";

export interface Customer {
    customerId: number;
    name: string;
    address: string;
    phone: string
    accounts: Account[];
}
