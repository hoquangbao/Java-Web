Create database MiniSocialNetwork

Use MiniSocialNetwork

Create table Account(
	email varchar(50) not null primary key,
	name nvarchar(255) not null,
	password nvarchar(255) not null,
	role int not null,
	status int not null
);

Create table Article(
	articleId int IDENTITY(1,1) primary key not null,
	title nvarchar(255) not null,
	description nvarchar(255),
	image nvarchar(255),
	createDate date not null,
	createBy varchar(50) not null,
	status int not null, 
);
alter table Article
Add foreign key (createBy) references Account(email);

Create table Comment(
	commentId int IDENTITY(1,1) primary key not null,
	email varchar(50) not null,
	articleId int not null,
	content nvarchar(255) not null,
	status int not null, 
);

alter table Comment
Add foreign key (articleId) references Article(articleId);

alter table Comment
Add foreign key (email) references Account(email);

Create table Emotion(
	emotionId int IDENTITY(1,1) primary key not null,
	createBy varchar(50) not null,
	articleId int not null,
	date date not null,
	status int not null,
);


alter table Emotion
Add foreign key (articleId) references Article(articleId);

alter table Emotion
Add foreign key (createBy) references Account(email);

Create table NotificationType(
	typeId int IDENTITY(1,1) primary key not null,
	notiType nvarchar(50) not null,
);

Create table Notification(
	notificationID int IDENTITY(1,1) primary key not null,
	articleId int not null,
	notiType int not null,
	createBy varchar(50) not null,
	createDate date not null,
	status int not null,
);

alter table Notification
Add foreign key (articleId) references Article(articleId);

alter table Notification
Add foreign key (createBy) references Account(email);

alter table Notification
Add foreign key (notiType) references NotificationType(typeId);


GO

