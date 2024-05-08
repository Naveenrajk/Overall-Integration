import { Component, Input, OnInit } from '@angular/core';  


import { MatDialog } from '@angular/material/dialog';
import { TexteditorComponent } from '../texteditor/texteditor.component';

import { ActiveCommentInterface } from '../Post-Comments/postComment-types/activeComment.interface';
import { Router } from '@angular/router';
import { Content } from '../Post-Model/content';
import { Reaction } from '../Post-Model/postreaction';
import { ContentService } from '../Post_Services/content.service';
import { ReactionService } from '../Post_Services/reaction.service';
import { PostCommentInterface } from '../Post-Comments/postComment-types/PostComment.interface';
import { PostCommentsService } from '../Post-Comments/postComment-services/PostComments.service';
import Swal from 'sweetalert2';
import { ContentDTO } from '../Post-Model/contentdto';
import { ContentValue } from '../Post-Model/contentValue';


@Component({  
  selector: 'app-pagecontent',  
  templateUrl: './pagecontent.component.html',  
  styleUrls: ['./pagecontent.component.css']  
})  
export class PagecontentComponent implements OnInit {  
  // res: any;  
  content:Content;
  Content:any;
  terms: any;  
  cont: any;
  date:Date;
  post: Content[]=[];
  pos: ContentDTO[]=[];
  contentList: Content[]=[];
  contentvalue:ContentValue;
  viewDiv:boolean=false;
  commentStatus!:boolean;
  reactionData : Reaction;
  
  value : any;
  result : string = "";
 
  reaction!: Reaction[];
 
  lists: any;
 
  selected!: number;
 
  userId:string='';
 
  count!: number;
 
  Reaction: any;
 
  reactionFlag: number = 0;
 
 
  PostComment: PostCommentInterface[]=[];
 
  PostComments: PostCommentInterface[]=[];
 
  User: any;
 
  isShow : boolean[]=[];
 
  isLike : boolean[]=[];

  @Input() currentUserId!: string;
 
  @Input() postId!: number;
  @Input() disableComments:boolean = false;
 
  activeComment: ActiveCommentInterface | null = null;
 
  posts:any | undefined;
  isHide:boolean[]=[];
 
  isHideComment = true;
  // commentStatus!:boolean;
  showTextEditor:boolean=true;
 
  constructor(private contentservice:ContentService,private reactionService: ReactionService,private router : Router,private dialog: MatDialog,private commentsService : PostCommentsService) {
    this.date=new Date;
 
    this.reactionData = new Reaction();
    this.Reaction;

    this.content = new Content;
    this.contentvalue = new ContentValue;
  }  
 
 
 
 
  isOptionIdHide = true;
 
 
 
  toggleDisplay(postId:number) {
   
    this.isShow[postId] = !this.isShow[postId];
    this.isLike[postId] = !this.isLike[postId];
   
  }
 
  toggleDisplayComment(postId: number) {
    if (this.post[postId].commentStatus === false) {
      this.isHide[postId] = !this.isHide[postId];
      this.isHideComment = !this.isHideComment;
    }
  }
 
  userValue:number=0;

  ngOnInit():void {  
    this.Getcontent();
    this.userId=sessionStorage.getItem('userId') || "";
    console.log(this.userId);
    this.userValue=parseInt(this.userId);
    
    const navigation=this.router.getCurrentNavigation();
    if(navigation && navigation.extras.state) {
      this.post=navigation.extras.state['posts'];
      this.disableComments=navigation.extras.state['disableComments']|| false;
    }
 
    if (!localStorage.getItem('viewpost')) {
      localStorage.setItem('viewpost', 'no reload')
      location.reload()
    } else {
      localStorage.removeItem('viewpost')
    }
  //   this.reactionService.getAllReactionCount().subscribe(ReactionCount => {
  //    this.Reaction = ReactionCount;
  //   //  console.log(this.Reaction);
  //  });
   this.count = 0;
   this.commentsService.getComments().subscribe((comments) => {
    this.PostComments=comments;
    console.log(comments);
  })
   }  
 
  TotalnumberofLikes:{[postId:number]:number}={};
  TotalnumberofDisLikes:number=25;
  flag:number=0;
  dislikeS :string = "dislike-button";
  likeS : string = "like-button";
 
 
 view(){
  this.viewDiv=true;
 }
 
  getRootComments(postId: number): PostCommentInterface[] {
    return this.PostComments.filter((comment) => (comment.parentId == "0" && comment.post.postId === postId));
    //return this.comments;
    console.log(this.PostComments);
  }
 
  updateComment({
    text,
    commentId,
  }: {
    text: string;
    commentId: string;
  }): void {
    this.commentsService
      .updateComment(commentId, text)
      .subscribe((updatedComment) => {
        this.PostComments = this.PostComments.map((comment) => {
          if (comment.id === commentId) {
            return updatedComment;
          }
          return comment;
        });
        this.activeComment = null;
      });
  }
 
  deleteComment(commentId: string): void {
    this.commentsService.deleteComment(commentId).subscribe(() => {
      this.PostComments = this.PostComments.filter(
        (comment) => comment.id !== commentId
      );
    });
  }
 
 
 
  setActiveComment(activeComment: ActiveCommentInterface | null): void {
    this.activeComment = activeComment;
  }
 
  ac:PostCommentInterface = new PostCommentInterface();
 
  // addComment({
  //   text,
  //   parentId,
  //   userId,
  //   postId: number
  // }: {
  //   text: string;
  //   parentId: string | null;
  //   userId: any,
  //   postId: number
  // }): void {
 
  //   //this.ac.pollId = this.ac.pollId;
  //   this.commentsService
  //     .createComment(text, parentId,this.postId,userId)
  //     .subscribe((createdComment) => {
  //       this.ac.userId="1";
  //       // createdComment.pollId=this.poll.pollId;
  //       this.comments = [...this.comments, createdComment];
  //       this.activeComment = null;
  //     });
  //     console.log(this.comments)
  // }
 
  getReplies(commentId: string): PostCommentInterface[] {
    return this.PostComments
      .filter((comment) => comment.parentId === commentId)
      //.filter((comment) => comment.parentId != "0")
      .sort(
        (a, b) =>
          new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
      );
  }
 
 
  getCommentsCount(postId:number):number {
    return this.PostComments.filter(comment => comment.post.postId === postId).length;
  }
 
    posrn:Reaction=new Reaction();
      reactionInsert(postId : number)
      {
             this.posrn.userId=1;
             this.posrn.postId=postId;
           
             if(this.TotalnumberofLikes[postId]){
               delete this.TotalnumberofLikes[postId];
               this.result = this.reactionService.reactionDelete(postId);
             }else{
               this.TotalnumberofLikes[postId]=1;
               this.result = this.reactionService.reactionInsert(this.posrn);
             }
             //  this.Reaction;
              console.log(this.result);
     
             if (this.TotalnumberofLikes[postId])
             {
             this.TotalnumberofLikes[postId]=1;
             }
             else{
               this.TotalnumberofLikes[postId]--;
             }
            }
     
 
  Getcontent()  
  {  
    this.contentservice. getAllPosts().subscribe((data:any)=>{  
      this.post=data; 
      this.pos=data;   
      console.log(data)
      console.log(this.post)
      this.terms= this.Content[1].pageContentTitle;  
      this.cont= this.Content[1].Content;  
      // console.log(this.Content);  
    })  
  }  
  receiveComment($event : any) {
    this.PostComments = $event;
    this.count = this.PostComments.length;
    console.log(this.PostComments.length);
  }
 
  recieveCount($event : any) {
    this.PostComments = $event;
    this.count = this.PostComments.length;
  }
  openPost(): void {
    const dialogRef = this.dialog.open(TexteditorComponent, {
      width: '1000px',
      // height: '400px',
      position: { top: "100px",left: "290px"},
     
    });
 
  }
 
 
  addCommentById({
    text,
    parentId,
    postId,
    userId,
     }: {
    text: any;
    parentId: any | null;
    postId: any
    userId: any,
   
  }): void {
 
    //this.ac.pollId = this.ac.pollId;
    this.commentsService
      .createComment(text, parentId,postId,userId)
      .subscribe()
       
     
      console.log(this.PostComments)
  }
 
  deletePost(id: any) {
    Swal.fire({
      title: "Are you sure you want to delete this post?",
      text: "This action cannot be undone!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes, delete it!'
    }).then((result) => {
      if (result.isConfirmed) {
        this.contentservice.deletePost(id).subscribe(data => {
          this.pos=this.pos.filter(post =>post.postId !==id)
          console.log(data);
          Swal.fire(
            'Deleted!',
            'Your post has been deleted.',
            'success'
          );
        },
        error => {
          console.error('Error deleting post:', error);
          Swal.fire(
            'Error!',
            'An error occurred while deleting the post.',
            'error'
          );
        });
      }
    });
  }

  editPost(data: any) {
    var str=data.map
 
    var splitter=str.split("+");
 
    this.contentvalue.id=splitter[0];
    this.contentvalue.value=splitter[1];
    this.contentvalue.postContent = data.postContent;
    // this.contentvalue.region = sessionStorage.getItem("regionId")||"";
    // this.contentvalue.department = sessionStorage.getItem("departmentId")||"";
    // this.contentvalue.project = this.deptValue;
    this.contentvalue.user=sessionStorage.getItem("userId")||"";
    console.log(this.contentvalue);
    this.contentvalue.status = true;
    this.contentvalue.commentStatus = this.commentStatus;

  Swal.fire({
    title: 'Post Submitted!',
    text: 'Your post has been successfully added.',
    icon: 'success',
    showCancelButton: false, 
    confirmButtonColor: '#3085d6', 
  }).then((result) => {
        this.router.navigateByUrl('/pageContent/${data.id}');
    if (result.isConfirmed) {
      this.contentservice.AddUpdateContent(this.contentvalue).subscribe((data: any) => {
        
        // this.router.navigateByUrl('/pageContent/${data.id}');
      },
    
      );
      
    }
    else{
      ( error: any) => {
        console.error("Error Saving Post: ", error);
        Swal.fire({
          title: 'Error!',
          text: 'An error occurred while saving your post. Please try again.',
          icon: 'error'
        });
      }
    }
  }
  );
}
openPostt(data:any){
  
  this.post=data;
  this.value=this.post;
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
     
      this.router.navigate(['/postedit'])
    }
  })



  }
 
togglepost():void{
  this.showTextEditor=!this.showTextEditor;
}
 
}