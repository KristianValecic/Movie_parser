create database MovieJAVA

use MovieJAVA
go 

create table Movie (
	IDMovie int primary key identity(1,1),
	Title nvarchar(250),
	PublishDate nvarchar(250),
	MovieDescription nvarchar(max),
	OriginalName nvarchar(250),
	Duration int,
	PosterPath nvarchar(250),
	StartDate nvarchar(250)
)
go

create table Person (
	IDPerson int primary key identity(1,1),
	Firstname nvarchar(250),
	Lastname nvarchar(250),
)
go

create table PersonRole (
	IDPersonRole int primary key identity(1,1),
	RoleName nvarchar(250),
)
go

/*fill person roles*/
insert into PersonRole(RoleName)
values('Director'), ('Actor'), ('Redatelj'), ('Glumac')
go

create table Genre (
	IDGenre int primary key identity(1,1),
	GenreName nvarchar(250),
)
go

create table AppUser (
	IDAppUser int primary key identity(1,1),
	Username nvarchar(250),
	Password nvarchar(250),
	UserRoleID int
)
go

create table AppUserRole (
	IDAppUserRole int primary key identity(1,1),
	RoleName nvarchar(250),
)
go

/*seed AppUser roles*/
insert into AppUserRole(RoleName)
values('Admin'), ('User')
go

/*seed AppUsers with roles*/
declare @adminID int
select @adminID = IDAppUserRole from AppUserRole
where RoleName = 'admin'
declare @UserID int
select @UserID = IDAppUserRole from AppUserRole
where RoleName = 'user'
insert into AppUser(Username, Password, UserRoleID)
values('admin', 'admin', @adminID), ('User', 'user', @UserID)
go


/*create table AssignedAppUserRoles (
	IDAssignedAppUserRoles int primary key identity(1,1),
	UserRoleID int,
	AppUserID int
)
go*/

create table MovieRole (
	IDMovieRole int primary key identity(1,1),
	MovieID int,
	PersonID int,
	PersonRoleID int,

	constraint fk_movieID foreign key (MovieID) references Movie(IDMovie),
	constraint fk_personID foreign key (PersonID) references Person(IDPerson),
	constraint fk_personRoleID foreign key (PersonRoleID) references PersonRole(IDPersonRole)
)
go

create table MovieGenre (
	IDMovieGenre int primary key identity(1,1),
	MovieID int,
	GenreID int,

	constraint fk_movieID_for_MovieGenre foreign key (MovieID) references Movie(IDMovie),
	constraint fk_genreID foreign key (GenreID) references Genre(IDGenre)
)
go

--PROCEDURES

create proc createMovie
	@Title NVARCHAR(250),
	@PublishDate NVARCHAR(250),
	@MovieDescription NVARCHAR(max),
	@OriginalName NVARCHAR(250),
	@Duration int,
	@PosterPath NVARCHAR(250),
	@StartDate NVARCHAR(250),
	@IDMovie INT OUTPUT
as
begin
	if not exists (select * from Movie where
	 Title = @Title and PublishDate = @PublishDate and MovieDescription = @MovieDescription and
	 OriginalName = @OriginalName and Duration = @Duration and PosterPath = @PosterPath and StartDate = @StartDate)
	begin
		INSERT INTO Movie 
		VALUES(@Title, @PublishDate, @MovieDescription, @OriginalName, @Duration, @PosterPath, @StartDate)
		SET @IDMovie = SCOPE_IDENTITY()
	end
end
go

create proc updateMovie
	@Title NVARCHAR(250),
	@PublishDate NVARCHAR(250),
	@MovieDescription NVARCHAR(max),
	@OriginalName NVARCHAR(250),
	@Duration int,
	@PosterPath NVARCHAR(250),
	@StartDate NVARCHAR(250),
	@IDMovie INT 
as
begin 
		UPDATE Movie SET 
		Title = @Title,
		PublishDate = @PublishDate,
		MovieDescription = @MovieDescription,
		OriginalName = @OriginalName,
		Duration = @Duration,
		PosterPath = @PosterPath,
		StartDate = @StartDate	
	WHERE 
		IDMovie = @IDMovie
end
go

create proc deleteMovie
	@IDMovie INT 
as
begin 
	Delete 
	from MovieGenre 
	where movieID = @IDMovie

	Delete 
	from MovieRole
	where movieID = @IDMovie

	Delete 
	from Movie
	where IDmovie = @IDMovie
end
go

create proc selectMovie
	@IDMovie INT 
as
begin 
	select *
	from Movie
	where IDMovie = @IDMovie
end
go

create proc selectAllMovies
as
begin 
	select *
	from Movie
end
go

create proc createPerson
	@Firstname nvarchar(250),
	@Lastname nvarchar(250),
	@IDPerson INT OUTPUT
as
begin
if	not exists (select * from Person where Firstname =  @Firstname and Lastname = @Lastname)
	begin
		INSERT INTO Person 
		VALUES(@Firstname, @Lastname)
		SET @IDPerson = SCOPE_IDENTITY()
	end
end
go

create proc updatePerson
	@Firstname nvarchar(250),
	@Lastname nvarchar(250),
	@IDPerson INT 
as
begin 
		UPDATE Person SET 
		Firstname = @Firstname,
		Lastname = @Lastname
	WHERE 
		IDPerson = @IDPerson
end
go

create proc deletePerson
	@IDPerson INT
as
begin
	delete from MovieRole
	where PersonID = @IDPerson

	Delete 
	from Person
	where IDPerson = @IDPerson
end
go

create proc selectPerson
	@IDPerson INT 
as
begin 
	select * 
	from Person
	/*inner join MovieRole on
	MovieRole.PersonID = IDPerson
	inner join PersonRole on
	MovieRole.PersonID = IDPerson*/
	where IDPerson = @IDPerson
end
go

create proc selectAllPeople
as
begin 
	select *
	from Person
end
go

create proc createPersonRole
	@RoleName nvarchar(250)
as
begin 
	if	not exists (select * from PersonRole where RoleName =  @RoleName)
	begin
		INSERT INTO PersonRole 
		VALUES(@RoleName)
	end
end
go

create proc createMovieRole
	@MovieID int,
	@PersonName nvarchar(250),--@PersonID int,	
	@RoleName nvarchar(250)
as
begin 
	declare @roleID int
	select @roleID = IDPersonRole from PersonRole where RoleName = @RoleName
	 
	declare @personID int
	select @personID = IDPerson from Person where --checks if person already exists
	Firstname = SUBSTRING(@PersonName, 0, CHARINDEX(' ', @PersonName)) and
	Lastname = SUBSTRING(@PersonName,  CHARINDEX(' ', @PersonName) +1 , len(@PersonName))

	if not exists (select * from MovieRole 
	where MovieID = @MovieID and PersonID = @personID and PersonRoleID = @roleID)
	begin
		insert into MovieRole(MovieID, PersonID, PersonRoleID)
		values (@MovieID, @PersonID, @roleID)
	end
end
go

create proc selectMovieRole
	@MovieID int,
	@RoleName nvarchar(100)
as
begin 
	select IDPerson, Firstname, Lastname, pr.RoleName from MovieRole as mr
	inner join Person as p on
	p.IDPerson = mr.PersonID
	inner join PersonRole as pr on
	mr.PersonRoleID = pr.IDPersonRole
	where MovieID = 960@MovieID and
	RoleName = @roleName
end
go

create proc deleteAllMovieRoles
	@MovieID int
as
begin 
	delete from MovieRole where MovieID = @MovieID
end
go



create proc createMovieGenre
	@MovieID int,
	@Genre nvarchar(250)
as
begin
if	not exists (select * from Genre where GenreName = @Genre)
	begin
		insert into Genre(GenreName)
		values (@Genre)
	end
		declare @idGenre int
		select @idGenre = idgenre from Genre where GenreName = @Genre
		insert into MovieGenre(MovieID, GenreID)
		values(@MovieID, @idGenre)
end
go

create proc selectMovieGenre
	@MovieID int
as
begin 
	select GenreName from MovieGenre as mg
	inner join Genre as g on
	g.IDGenre = mg.GenreID
	where MovieID = @MovieID 
end
go

create proc deleteGenre
	@Genre nvarchar(250)
as
begin 
	declare @genreid int
	select @genreid = idgenre from Genre 
	where GenreName = @genre

	delete from MovieGenre
	where GenreID = @genreid

	delete from genre 
	where IDGenre = @genreid
end
go

create proc createGenre
	@Genre nvarchar(250),
	@IDGenre int output
as
begin
if	not exists (select * from Genre where GenreName = @Genre)
	begin
		insert into Genre(GenreName)
		values (@Genre)
		SET @IDGenre = SCOPE_IDENTITY()
	end
end
go

create proc deleteAllMovieGenres
	@MovieID int 
as
begin
	delete from MovieGenre
	where MovieID = @MovieID
end
go

create proc deleteAll
	@MovieID int
as
begin 
	Delete 
	from MovieGenre 

	Delete 
	from MovieRole

	Delete 
	from Movie
	
	Delete 
	from Person
end
go

create proc checkIfUserExists
	@Username nvarchar(250),
	@Password nvarchar(250)
as
begin
	select * from AppUser as u
	inner join appuserrole as r on
	u.UserRoleID = r.IDAppUserRole
	where Username =  @Username and Password = @Password

end
go

create proc checkIfUsernameExists
	@Username nvarchar(250)
as
begin
	if exists (select * from AppUser where Username = @Username)
		begin
			select 1 as 'result' 
		end
	else
		begin
			select 0 as 'result'
		end
end
go

create proc createUser
	@Username nvarchar(250),
	@Password nvarchar(250),
	@RoleName nvarchar(250),
	@IDAppUser int output
as
begin
--if	not exists (select * from Person where Firstname =  @Firstname and Lastname = @Lastname
	declare @userRoleID int
	select @userRoleID = IDAppUserRole from AppUserRole where RoleName = @RoleName
	INSERT INTO AppUser(Username, Password, UserRoleID) 
	VALUES(@Username, @Password, @userRoleID)
	SET @IDAppUser = SCOPE_IDENTITY()
end
go

create proc selectAllGenres
as
begin 
	select *
	from Genre
end
go

