import { User } from "./User";

export class Invite {
    userEmailId!: string ;

    invitationId !: number;
    sender! : User;
    recipientEmail! : string;
    invitationCode! : string;
    sentTime ! : string;
    accepted! : boolean;
    acceptedTime!: string;

    
  }