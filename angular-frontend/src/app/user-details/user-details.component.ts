import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {

  id: number=0
  user: User = new User;
  constructor(private router: ActivatedRoute,private userService: UserService) { }

  ngOnInit(): void {
    this.id=this.router.snapshot.params['id'];

    this.user= new User();
    this.userService.getUserById(this.id).subscribe( data=>{
      this.user = data;
    })
  }

}
