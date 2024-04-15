export interface Movement {

    movementId: number;
    amount: number;
    movementDate: Date;

    accountId: number;
    numberAccount: string;

    movementTypeId: string;
    movementTypeDescription: string;
}
