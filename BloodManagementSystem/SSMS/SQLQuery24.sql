USE [BloodManagementDB]
GO

/****** Object:  Table [dbo].[Donors]    Script Date: 2/7/2026 2:40:21 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Donors](
	[DonorID] [int] IDENTITY(1,1) NOT NULL,
	[FirstName] [nvarchar](50) NOT NULL,
	[LastName] [nvarchar](50) NOT NULL,
	[Gender] [char](1) NULL,
	[DateOfBirth] [date] NOT NULL,
	[BloodGroupID] [int] NOT NULL,
	[Email] [varchar](100) NULL,
	[PhoneNumber] [varchar](20) NOT NULL,
	[Address] [nvarchar](max) NULL,
	[CreatedBy] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[DonorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[Donors]  WITH CHECK ADD FOREIGN KEY([CreatedBy])
REFERENCES [dbo].[AppUsers] ([UserID])
GO

ALTER TABLE [dbo].[Donors]  WITH CHECK ADD  CONSTRAINT [FK_Donors_BloodGroups] FOREIGN KEY([BloodGroupID])
REFERENCES [dbo].[BloodGroups] ([BloodGroupID])
GO

ALTER TABLE [dbo].[Donors] CHECK CONSTRAINT [FK_Donors_BloodGroups]
GO

ALTER TABLE [dbo].[Donors]  WITH CHECK ADD CHECK  (([Gender]='F' OR [Gender]='M'))
GO


