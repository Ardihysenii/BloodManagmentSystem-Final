USE [BloodManagementDB]
GO

/****** Object:  Table [dbo].[BloodRequests]    Script Date: 2/7/2026 2:39:48 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[BloodRequests](
	[RequestID] [int] IDENTITY(1,1) NOT NULL,
	[HospitalID] [int] NOT NULL,
	[BloodGroupID] [int] NOT NULL,
	[RequestedUnits] [int] NOT NULL,
	[UrgencyLevel] [nvarchar](20) NULL,
	[RequestDate] [datetime] NULL,
	[Status] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[RequestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[BloodRequests] ADD  DEFAULT ((1)) FOR [RequestedUnits]
GO

ALTER TABLE [dbo].[BloodRequests] ADD  DEFAULT (getdate()) FOR [RequestDate]
GO

ALTER TABLE [dbo].[BloodRequests] ADD  DEFAULT ('Pending') FOR [Status]
GO

ALTER TABLE [dbo].[BloodRequests]  WITH CHECK ADD  CONSTRAINT [FK_Requests_BloodGroup] FOREIGN KEY([BloodGroupID])
REFERENCES [dbo].[BloodGroups] ([BloodGroupID])
GO

ALTER TABLE [dbo].[BloodRequests] CHECK CONSTRAINT [FK_Requests_BloodGroup]
GO

ALTER TABLE [dbo].[BloodRequests]  WITH CHECK ADD  CONSTRAINT [FK_Requests_Hospital] FOREIGN KEY([HospitalID])
REFERENCES [dbo].[Hospitals] ([HospitalID])
GO

ALTER TABLE [dbo].[BloodRequests] CHECK CONSTRAINT [FK_Requests_Hospital]
GO

ALTER TABLE [dbo].[BloodRequests]  WITH CHECK ADD CHECK  (([Status]='Completed' OR [Status]='Rejected' OR [Status]='Approved' OR [Status]='Pending'))
GO


