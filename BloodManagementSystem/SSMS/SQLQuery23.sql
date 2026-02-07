USE [BloodManagementDB]
GO

/****** Object:  Table [dbo].[Donations]    Script Date: 2/7/2026 2:40:11 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[Donations](
	[DonationID] [int] IDENTITY(1,1) NOT NULL,
	[DonorID] [int] NOT NULL,
	[DonationDate] [datetime] NULL,
	[VolumeML] [int] NOT NULL,
	[BloodPressure] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[DonationID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[Donations] ADD  DEFAULT (getdate()) FOR [DonationDate]
GO

ALTER TABLE [dbo].[Donations]  WITH CHECK ADD  CONSTRAINT [FK_Donations_Donors] FOREIGN KEY([DonorID])
REFERENCES [dbo].[Donors] ([DonorID])
ON DELETE CASCADE
GO

ALTER TABLE [dbo].[Donations] CHECK CONSTRAINT [FK_Donations_Donors]
GO

ALTER TABLE [dbo].[Donations]  WITH CHECK ADD CHECK  (([VolumeML]>(0)))
GO


