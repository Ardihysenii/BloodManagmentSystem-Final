USE [BloodManagementDB]
GO

/****** Object:  Table [dbo].[BloodStock]    Script Date: 2/7/2026 2:39:57 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[BloodStock](
	[StockID] [int] IDENTITY(1,1) NOT NULL,
	[BloodGroupID] [int] NOT NULL,
	[DonationID] [int] NULL,
	[ExpiryDate] [date] NOT NULL,
	[Status] [nvarchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[StockID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[BloodStock] ADD  DEFAULT ('Available') FOR [Status]
GO

ALTER TABLE [dbo].[BloodStock]  WITH CHECK ADD  CONSTRAINT [FK_Stock_BloodGroup] FOREIGN KEY([BloodGroupID])
REFERENCES [dbo].[BloodGroups] ([BloodGroupID])
GO

ALTER TABLE [dbo].[BloodStock] CHECK CONSTRAINT [FK_Stock_BloodGroup]
GO

ALTER TABLE [dbo].[BloodStock]  WITH CHECK ADD  CONSTRAINT [FK_Stock_Donation] FOREIGN KEY([DonationID])
REFERENCES [dbo].[Donations] ([DonationID])
GO

ALTER TABLE [dbo].[BloodStock] CHECK CONSTRAINT [FK_Stock_Donation]
GO

ALTER TABLE [dbo].[BloodStock]  WITH CHECK ADD CHECK  (([Status]='Reserved' OR [Status]='Expired' OR [Status]='Used' OR [Status]='Available'))
GO


