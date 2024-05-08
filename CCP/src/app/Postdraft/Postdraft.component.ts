import { Component, OnInit } from '@angular/core';
import { Content } from '../Post-Model/content';

import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { TexteditorComponent } from '../texteditor/texteditor.component';
import { DrafteditComponent } from '../draftedit/draftedit.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ContentService } from '../Post_Services/content.service';
import { ReactionService } from '../Post_Services/reaction.service';
import Swal from 'sweetalert2';
import { ContentDTO } from '../Post-Model/contentdto';


@Component({
  selector: 'app-draft',
  templateUrl: './Postdraft.component.html',
  styleUrls: ['./Postdraft.component.css']
})
export class PostdraftComponent implements OnInit {
  isFeedListVisible:boolean=false;
  draftCount:number=0;

  Content: any;
  terms: any;
  cont: any;
  date: Date;
  content: Content[] = [];
  //md
  posts:Content[]=[];
  pos:ContentDTO[]=[];

  con!: Content;
  post: Content ;

  result: string = "";



  lists: any;

  selected!: number;

  userId:string='';
  
  count!: number;

  Reaction: any;

  reactionFlag: number = 0;

value : any;

  comments!: string;

  User: any;

  

  constructor(public contentservice: ContentService, private reactionService: ReactionService, private router: Router, private dialog: MatDialog) {
    this.date = new Date;

    this.Reaction;
    this.post=new Content;

  }

  isShow = true;

  isLike = false;
  likedPostId: number | null = null;

  isOptionIdHide = true;

  isHide = true;


  toggleDisplay(postId: number) {
    if (this.likedPostId === postId) {
      this.isShow = !this.isShow;
      this.isLike = !this.isLike;
    }
  }

  toggleDisplayComment() {
    this.isHide = !this.isHide;
  }

  userValue:number=0;

  ngOnInit() {
    this.Getcontent();
    this.userId=sessionStorage.getItem('userId') || "";
    console.log(this.userId);
    this.userValue=parseInt(this.userId)


    if (!localStorage.getItem('viewpost')) {
      localStorage.setItem('viewpost', 'no reload')
      location.reload()
    } else {
      localStorage.removeItem('viewpost')
    }

    this.count = 0;

    this.contentservice.getAllDrafts() // Include drafts
    .subscribe(posts => {
      this.draftCount = posts.filter(post => post).length;
    });
  }

  TotalnumberofLikes: { [postId: number]: number } = {};
  TotalnumberofDisLikes: number = 25;
  flag: number = 0;
  dislikeS: string = "dislike-button";
  likeS: string = "like-button";


  Getcontent() {
    this.contentservice.getAllDrafts().subscribe((data: any) => {
       this.post=data; 
      this.pos=data;
      console.log(data)
      console.log(this.post)
      this.terms = this.Content[1].pageContentTitle;
      this.cont = this.Content[1].Content;

    })
  }


  // deletecontent(postId: number) {
    
  //    alert("Do you want to delete this Draft");
  //   this.contentservice.deleteDraft(postId).subscribe(
  //     (response: any) => {
  //       console.log('Post deleted successfully:', response);

  //       this.Content = this.Content.filter((content: Content) => content.postId !== postId);

  //     },
  //     (error: any) => {
  //       console.error('Error deleting post:', error);

  //     }
      
  //   );
    
  // }

  deletecontent(postId: number) {
    Swal.fire({
      title: "Are you sure you want to delete this draft?",
      text: "This action cannot be undone!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.contentservice.deleteDraft(postId).subscribe(
          (response: any) => {
            console.log('Post deleted successfully:', response);
  
            // this.Content = this.Content.filter((content: Content) => content.postId !== postId);
            this.pos=this.pos.filter(post =>post.postId !==postId);
  
            Swal.fire(
              'Deleted!',
              'Your draft has been deleted.',
              'success'
            );
          },
          (error: any) => {
            console.error('Error deleting post:', error);
  
            Swal.fire(
              'Error!',
              'An error occurred while deleting the draft.',
              'error'
            );
          }
        );
      }
    });
  }
  


  receiveComment($event: any) {
    this.comments = $event;
    this.count = this.comments.length;
    console.log(this.comments.length);
  }

  recieveCount($event: any) {
    this.comments = $event;
    this.count = this.comments.length;
  }




  openPost(data:any){
  
    this.post=data;
    this.value=this.post.postId;
    sessionStorage.setItem("editId",this.value);
      //   // alert("Do you want to edit this Draft");
    Swal.fire({
      title: 'Do you want to edit this Draft',
      icon: 'success',
      showCancelButton: false,
      confirmButtonColor: '#1B193F',
      confirmButtonText: 'Ok!',
      customClass: {
    popup: 'my-popup-class',
  },
    }).then((result) =>{
      if(result.isConfirmed){
       
        this.router.navigate(['/editdraft'])
      }
    })
  

  
    }
  
 
  
}
