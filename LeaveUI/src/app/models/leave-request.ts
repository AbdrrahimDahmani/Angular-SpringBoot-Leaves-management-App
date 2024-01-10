export interface LeaveRequest {
        start_date: string;
        end_date: string;
        status: string;
        comment: string;
        leaveType: string;
        user:{
          id:number
        }
      }
      

